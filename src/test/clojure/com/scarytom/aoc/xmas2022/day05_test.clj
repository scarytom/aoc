(ns com.scarytom.aoc.xmas2022.day05-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc.xmas2022.day05 :as day05]))

(def sample-lines ["    [D]    "
                   "[N] [C]    "
                   "[Z] [M] [P]"
                   " 1   2   3 "
                   ""
                   "move 1 from 2 to 1"
                   "move 3 from 1 to 3"
                   "move 2 from 2 to 1"
                   "move 1 from 1 to 2"])

(deftest decode-stacks
  (is (= (day05/decode-stacks (take 4 sample-lines))
         ['(\N \Z)
          '(\D \C \M)
          '(\P)])))

(deftest decode-moves
  (is (= (day05/decode-moves (drop 5 sample-lines))
         [[1 1 0]
          [3 0 2]
          [2 1 0]
          [1 0 1]])))

(deftest perform-moves-9000
  (is (= (day05/perform-moves :9000
                              ['(\N \Z)
                               '(\D \C \M)
                               '(\P)]
                              [[1 1 0]
                               [3 0 2]
                               [2 1 0]
                               [1 0 1]])
         ['(\C)
          '(\M)
          '(\Z \N \D \P)])))

(deftest perform-moves-9001
  (is (= (day05/perform-moves :9001
                              ['(\N \Z)
                               '(\D \C \M)
                               '(\P)]
                              [[1 1 0]
                               [3 0 2]
                               [2 1 0]
                               [1 0 1]])
         ['(\M)
          '(\C)
          '(\D \N \Z \P)])))


(deftest part-1-solution
  (is (= (day05/part-1) "WHTLRMZRC")))

(deftest part-2-solution
  (is (= (day05/part-2) "GMPMLWNMG")))