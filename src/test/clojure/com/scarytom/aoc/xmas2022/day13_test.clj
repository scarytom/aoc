(ns com.scarytom.aoc.xmas2022.day13-test
  (:require
    [clojure.test :refer [deftest is]]
    [com.scarytom.aoc.xmas2022.day13 :as day13]))

(def sample-lines ["[1,1,3,1,1]"
                   "[1,1,5,1,1]"
                   ""
                   "[[1],[2,3,4]]"
                   "[[1],4]"
                   ""
                   "[9]"
                   "[[8,7,6]]"
                   ""
                   "[[4,4],4,4]"
                   "[[4,4],4,4,4]"
                   ""
                   "[7,7,7,7]"
                   "[7,7,7]"
                   ""
                   "[]"
                   "[3]"
                   ""
                   "[[[]]]"
                   "[[]]"
                   ""
                   "[1,[2,[3,[4,[5,6,7]]]],8,9]"
                   "[1,[2,[3,[4,[5,6,0]]]],8,9]"])

(deftest compare-packets-test
  (is (= (day13/compare-packets [1,1,3,1,1] [1,1,5,1,1]) -1))
  (is (= (day13/compare-packets [[1],[2,3,4]] [[1],4]) -1))
  (is (= (day13/compare-packets [9] [[8,7,6]]) 1))
  (is (= (day13/compare-packets [[4,4],4,4] [[4,4],4,4,4]) -1))
  (is (= (day13/compare-packets [7,7,7,7] [7,7,7]) 1))
  (is (= (day13/compare-packets [] [3]) -1))
  (is (= (day13/compare-packets [[[]]] [[]]) 1))
  (is (= (day13/compare-packets [1,[2,[3,[4,[5,6,7]]]],8,9] [1,[2,[3,[4,[5,6,0]]]],8,9]) 1)))

(deftest finds-the-indexes-of-ordered-pairs
  (is (= (day13/ordered-pair-numbers sample-lines)
         [1 2 4 6])))

(deftest calculates-decoder-key
  (is (= (day13/decoder-key sample-lines)
         140)))

(deftest part-1-solution
  (is (= (day13/part-1) 6272)))

(deftest part-2-solution
  (is (= (day13/part-2) 22288)))