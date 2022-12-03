(ns com.scarytom.aoc.xmas2022.day03-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc.xmas2022.day03 :as day03]))

(def sample-lines ["vJrwpWtwJgWrhcsFMMfFFhFp"
                   "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
                   "PmmdzqPrVvPwwTWBwg"
                   "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
                   "ttgJtRGJQctTZtZT"
                   "CrZsJsPPZsGzwwsLwLmpwMDw"])

(deftest prioritises-an-item
  (is (= (day03/priority \a) 1))
  (is (= (day03/priority \z) 26))
  (is (= (day03/priority \A) 27))
  (is (= (day03/priority \Z) 52)))

(deftest prioritises-things
  (is (= (day03/prioritise (take 3 sample-lines))
         (day03/priority \r))))