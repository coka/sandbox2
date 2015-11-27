"use strict";

module.exports = function StringCalculator() {

  function add(input) {

    if (input === "") {
      return 0;
    }

    function toInteger(currentValue, index, array) {
      return parseInt(currentValue, 10);
    }

    function sum(previousValue, currentValue, currentIndex, array) {
      return previousValue + currentValue;
    }

    return input.split(",").map(toInteger).reduce(sum);
  }

  return { add : add };
}();
