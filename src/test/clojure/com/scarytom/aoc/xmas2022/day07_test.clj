(ns com.scarytom.aoc.xmas2022.day07-test
  (:require [clojure.test :refer [deftest is]]
            [com.scarytom.aoc.xmas2022.day07 :as day07]))

(def sample-lines ["$ cd /"
                   "$ ls"
                   "dir a"
                   "14848514 b.txt"
                   "8504156 c.dat"
                   "dir d"
                   "$ cd a"
                   "$ ls"
                   "dir e"
                   "29116 f"
                   "2557 g"
                   "62596 h.lst"
                   "$ cd e"
                   "$ ls"
                   "584 i"
                   "$ cd .."
                   "$ cd .."
                   "$ cd d"
                   "$ ls"
                   "4060174 j"
                   "8033020 d.log"
                   "5626152 d.ext"
                   "7214296 k"])

(deftest read-tree
  (is (= (day07/read-directory-tree sample-lines)
         {'("/")         48381165
          '("d" "/")     24933642
          '("a" "/")     94853
          '("e" "a" "/") 584})))

(deftest part-1-solution
  (is (= (day07/part-1) 1443806)))

(deftest part-2-solution
  (is (= (day07/part-2) 942298)))