(ns hobbit-crusher.core-test
  (:require [clojure.test :refer :all]
            [hobbit-crusher.core :refer :all]))

(def left-body-part {:name "left arm" :size 2})

(def right-body-part {:name "right arm" :size 2})

(def unmatchable-body-part {:name "head" :size 3})

(def cleft-chin {:name "cleft chin" :size 1})

(def symmetric-hobbit-body-parts
  [{:name "head" :size 3}
   {:name "left eye" :size 1}
   {:name "right eye" :size 1}
   {:name "left ear" :size 1}
   {:name "right ear" :size 1}
   {:name "kisser" :size 1}
   {:name "cleft chin" :size 1}
   {:name "tender neck" :size 2}
   {:name "left shoulder" :size 1}
   {:name "right shoulder" :size 1}
   {:name "left arm" :size 2}
   {:name "right arm" :size 2}
   {:name "chest" :size 3}
   {:name "abdominal" :size 2}
   {:name "unmentionable" :size 1}
   {:name "pair of unmentionables" :size 2}
   {:name "left thigh" :size 2}
   {:name "right thigh" :size 2}
   {:name "left knee" :size 1}
   {:name "right knee" :size 1}
   {:name "left shin" :size 2}
   {:name "right shin" :size 2}
   {:name "left foot" :size 1}
   {:name "right foot" :size 1}])

(deftest test-symmetrize-body-part

  (testing "Given a left body part"
    (let [result (symmetrize-body-part left-body-part)]

      (testing "returns a collection containing two items"
        (is (= 2 (count result))))

      (testing "returns a collection containing the right body part"
        (is (some #(= % right-body-part) result)))

      (testing "returns a vector"
        (is (vector? result)))

      (testing "returns a vector in which the left part comes first"
        (is (= left-body-part (first result))))))

  (testing "Given an unmatchable body part"
    (let [result (symmetrize-body-part unmatchable-body-part)]

      (testing "returns a collection containing one item"
        (is (= 1 (count result))))

      (testing "returns a vector"
        (is (vector? result)))

      (testing "returns a vector containing the given body part"
        (is (some #(= % unmatchable-body-part) result)))))

  (testing "Given a cleft chin"

    (testing "doesn't get confused"
      (is (= [cleft-chin]
             (symmetrize-body-part cleft-chin))))))

(deftest test-symmetrize-body

  (testing "Returns a correctly symmetrized vector of body parts"
    (is (= symmetric-hobbit-body-parts
           (symmetrize-body asymmetric-hobbit-body-parts)))))
