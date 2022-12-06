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
