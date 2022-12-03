(ns com.scarytom.aoc-2022.day03-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc-2022.day03 :as day03]))

(def sample-lines ["vJrwpWtwJgWrhcsFMMfFFhFp"
                   "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
                   "PmmdzqPrVvPwwTWBwg"
                   "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
                   "ttgJtRGJQctTZtZT"
                   "CrZsJsPPZsGzwwsLwLmpwMDw"])

(deftest reads-sack-contents
  (is (= (day03/sack-contents (take 1 sample-lines))
         [[[\v \J \r \w \p \W \t \w \J \g \W \r]
           [\h \c \s \F \M \M \f \F \F \h \F \p]]])))

(deftest prioritises-an-item
  (is (= (day03/priority \a) 1))
  (is (= (day03/priority \z) 26))
  (is (= (day03/priority \A) 27))
  (is (= (day03/priority \Z) 52))
  )

(deftest prioritises-sacks
  (is (= (->> sample-lines
              day03/sack-contents
              (map day03/prioritise-sack))
         (map day03/priority [\p \L \P \v \t \s]))))

(deftest finds-badge
  (is (= (day03/find-badge (day03/sack-contents (take 3 sample-lines)))
         \r)))