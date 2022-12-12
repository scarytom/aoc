(ns com.scarytom.aoc.xmas2022.day09-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc.xmas2022.day09 :as day09]))

(def sample-lines-1 ["R 4"
                     "U 4"
                     "L 3"
                     "D 1"
                     "R 4"
                     "D 1"
                     "L 5"
                     "R 2"])

(deftest count-tail-positions-for-two-knot-rope
  (is (= (day09/count-tail-positions 2 sample-lines-1)
         13)))

(deftest count-tail-positions-for-ten-knot-rope
  (is (= (day09/count-tail-positions 10 sample-lines-1)
         1)))

(def sample-lines-2 ["R 5"
                     "U 8"
                     "L 8"
                     "D 3"
                     "R 17"
                     "D 10"
                     "L 25"
                     "U 20"])

#_(deftest count-tail-positions-for-ten-knot-rope-mk-2
  (is (= (distinct (day09/calc-tail-positions 11 sample-lines-2))
         []))

  (is (= (day09/count-tail-positions 10 sample-lines-2)
         36)))

(deftest part-1-solution
  (is (= (day09/part-1) 6406)))

#_(deftest part-2-solution
  (is (= (day09/part-2) 1)))