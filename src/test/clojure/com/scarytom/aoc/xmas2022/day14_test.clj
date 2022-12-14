(ns com.scarytom.aoc.xmas2022.day14-test
  (:require
    [clojure.test :refer [deftest is]]
    [com.scarytom.aoc.xmas2022.day14 :as day14]))

(def sample-lines ["498,4 -> 498,6 -> 496,6"
                   "503,4 -> 502,4 -> 502,9 -> 494,9"])

(deftest read-filled-coords-test
  (is (= (day14/read-filled-coords "498,4 -> 498,6 -> 496,6")
         #{[498 4] [498 5] [498 6] [497 6] [496 6]})))

(deftest reads-structure
  (is (= (day14/read-structure sample-lines)
         #{[498 4] [498 5] [498 6]
           [497 6] [496 6]
           [503 4] [502 4]
           [502 5] [502 6] [502 7] [502 8] [502 9]
           [501 9] [500 9] [499 9] [498 9] [497 9] [496 9] [495 9] [494 9]})))

(deftest finds-next-sand-rest-coord
  (is (= (day14/find-sand-rest-coord (day14/read-structure sample-lines) [500 0] 9 true)
         [500 8])))

(deftest count-sand-pt-1
  (is (= (day14/count-sand true sample-lines)
         24)))

(deftest count-sand-pt-2
  (is (= (day14/count-sand false sample-lines)
         93)))

(deftest part-1-solution
  (is (= (day14/part-1) 1133)))

(deftest part-2-solution
  (is (= (day14/part-2) 27566)))