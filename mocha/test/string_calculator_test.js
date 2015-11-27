"use strict";

var assert = require("assert");
var StringCalculator = require("../lib/string_calculator.js");

describe("StringCalculator", function() {

  describe(".add", function() {

    context("given an empty string", function() {

      it("returns zero", function() {
        assert.equal(0, StringCalculator.add(""));
      });
    });

    context("single numbers", function() {

      context("given '4'", function() {

        it("returns 4", function() {
          assert.equal(4, StringCalculator.add("4"));
        });
      });

      context("given '10'", function() {

        it("returns 10", function() {
          assert.equal(10, StringCalculator.add("10"));
        });
      });
    });

    context("comma-separated numbers", function() {

      context("given '2,4'", function() {

        it("returns 6", function() {
          assert.equal(6, StringCalculator.add("2,4"));
        });
      });

      context("given '17,100'", function() {

        it("returns 117", function() {
          assert.equal(117, StringCalculator.add("17,100"));
        });
      });
    });
  });
});
