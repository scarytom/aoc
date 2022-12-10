(ns com.scarytom.aoc.xmas2022.day08-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc.xmas2022.day08 :as day08]))

(def sample-lines ["30373"
                   "25512"
                   "65332"
                   "33549"
                   "35390"])

(deftest read-grid
  (is (= (map #(map :height %) (day08/read-grid sample-lines))
         [[3 0 3 7 3]
          [2 5 5 1 2]
          [6 5 3 3 2]
          [3 3 5 4 9]
          [3 5 3 9 0]])))

(deftest find-visible-trees
  (is (= (day08/find-visible-trees sample-lines)
         #{[0 0] [1 0] [2 0] [3 0] [4 0]
           [0 1] [1 1] [2 1]       [4 1]
           [0 2] [1 2]       [3 2] [4 2]
           [0 3]       [2 3]       [4 3]
           [0 4] [1 4] [2 4] [3 4] [4 4]}))

  (is (= (count (day08/find-visible-trees sample-lines)) 21)))


(deftest scores-viewing-distance
  (is (= (day08/score [5 3]) 1))
  (is (= (day08/score [5 5]) 1))
  (is (= (day08/score [5 5 5]) 1))
  (is (= (day08/score [5 3 5]) 2)))


(deftest part-1-solution
  (is (= (day08/part-1) 1801)))

(deftest part-2-solution
  (is (= (day08/part-2) 209880)))