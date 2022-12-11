(ns com.scarytom.aoc.xmas2022.day09-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc.xmas2022.day09 :as day09]))

(def sample-lines ["R 4"
                   "U 4"
                   "L 3"
                   "D 1"
                   "R 4"
                   "D 1"
                   "L 5"
                   "R 2"])


(deftest part-1-solution
  (is (= (day09/part-1) 6406)))

#_(deftest part-2-solution
  (is (= (day09/part-2) 1)))