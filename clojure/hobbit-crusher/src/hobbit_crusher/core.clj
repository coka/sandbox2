(ns hobbit-crusher.core)

(def asymmetric-hobbit-body-parts
  [{:name "head" :size 3}
   {:name "left eye" :size 1}
   {:name "left ear" :size 1}
   {:name "kisser" :size 1}
   {:name "cleft chin" :size 1}
   {:name "tender neck" :size 2}
   {:name "left shoulder" :size 1}
   {:name "left arm" :size 2}
   {:name "chest" :size 3}
   {:name "abdominal" :size 2}
   {:name "unmentionable" :size 1}
   {:name "pair of unmentionables" :size 2}
   {:name "left thigh" :size 2}
   {:name "left knee" :size 1}
   {:name "left shin" :size 2}
   {:name "left foot" :size 1}])

(def hobbit-names
  ["Frodo" "Sam" "Merry" "Pippin"])

(def primal-epithets
  ["SAVAGE" "MONSTER" "BEAST"])

(defn symmetrize-body-part
  "Produces a vector of symmetric body parts, if possible. Otherwise, returns a
  vector containing only the given part."
  [part]
  (let [pair {:name (clojure.string/replace (:name part) #"^left" "right")
              :size (:size part)}]
    (if (= part pair)
      [part]
      [part pair])))

(defn symmetrize-body
  "Forges a perfectly symmetric body from a vector of asymmetric body parts."
  [asymmetric-body-parts]
  (loop [remaining-parts asymmetric-body-parts
         forged-body []]
    (if (empty? remaining-parts)
      (flatten forged-body)
      (let [[part & remaining] remaining-parts]
        (recur remaining
               (into forged-body (symmetrize-body-part part)))))))

(defn get-random-body-part
  [body]
  (let [body-size (reduce + (map :size body))
        target (rand-int body-size)]
    (loop [[part & remaining] body
           accumulator (:size part)]
      (if (> accumulator target)
        part
        (recur remaining (+ accumulator (:size (first remaining))))))))

(defn crush-hobbit
  "Crushes whichever body part of whichever hobbit your primal ferocity gets its
  hands on first. Has quite the side-effect."
  []
  (let [body (symmetrize-body asymmetric-hobbit-body-parts)
        crushed-part (get-random-body-part body)
        gory-details (str "You crushed "
                          (rand-nth hobbit-names) "'s "
                          (:name crushed-part) "! "
                          "You " (rand-nth primal-epithets) "!")]
    (println gory-details)))

(defn thrice
  "Executes a function three times (presumably for side-effects)."
  [function]
  (dotimes [n 3] (function)))

(defn -main
  "Crushes thrice the filthy little hobbitses. No arguments."
  []
  (thrice crush-hobbit)
  (println "And thus, the hobbits had been crushed thrice."))
