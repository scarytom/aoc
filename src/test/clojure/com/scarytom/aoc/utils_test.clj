(ns com.scarytom.aoc.utils-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc.utils :as utils]))


(deftest reads-sack-contents
  (is (= (utils/chop 2 "vJrwpWtwJgWrhcsFMMfFFhFp")
         [[\v \J \r \w \p \W \t \w \J \g \W \r]
          [\h \c \s \F \M \M \f \F \F \h \F \p]])))

(deftest ring-buffer-test
  (is (= (vec (utils/ring-buffer 3))
         []))

  (is (= (-> (utils/ring-buffer 3)
             (conj :a)
             (conj :b)
             (conj :c)
             (conj :d)
             (vec))
         [:b :c :d])))

(deftest filter-ascending-test
  (is (= (utils/filter-ascending [7 1 3]) [7]))
  (is (= (utils/filter-ascending [2 5 5 1]) [2 5]))
  (is (= (utils/filter-ascending [2 1]) [2]))
  (is (= (utils/filter-ascending [9 4 3 1]) [9]))
  (is (= (utils/filter-ascending [2 3 1 5 4 6]) [2 3 5 6])))

(deftest explode-grid-test
  (is (= (utils/explode-grid [[1 2 3]
                              [4 5 6]
                              [7 8 9]])
         [
          ; rows
          [1 2 3]
          [4 5 6]
          [7 8 9]

          ; columns
          [1 4 7]
          [2 5 8]
          [3 6 9]

          ; reversed rows
          [3 2 1]
          [6 5 4]
          [9 8 7]

          ; reversed columns
          [7 4 1]
          [8 5 2]
          [9 6 3]])))

(deftest simplify-ranges-test
  (is (= (sort (utils/simplify-ranges [[1 10] [3 6] [8 11] [11 12] [13 14] [22 23]]))
         [[1 12] [13 14] [22 23]])))
