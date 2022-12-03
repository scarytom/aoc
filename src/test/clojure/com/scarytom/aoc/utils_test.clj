(ns com.scarytom.aoc.utils-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc.utils :as utils]))


(deftest reads-sack-contents
  (is (= (utils/chop 2 "vJrwpWtwJgWrhcsFMMfFFhFp")
         [[\v \J \r \w \p \W \t \w \J \g \W \r]
          [\h \c \s \F \M \M \f \F \F \h \F \p]])))