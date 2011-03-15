(ns smoothing.moving-averages)

(defn- average [lst] (/ (reduce + lst) (count lst)))
(defn simple-moving-average [window lst] (map average (partition window 1 lst)))

(defn- weighted-average [lst]
  (let [indices (map (partial + 1) (range (count lst)))
	total (reduce + indices)
	zipped-list (map vector indices lst)]
    (reduce #(+ (/ (* (first %2) (second %2)) total) %1) 0 zipped-list)))
(defn weighted-moving-average [window lst] (map weighted-average (partition window 1 lst)))

(defn- exponentially-weighted-average [lst alpha]
  (reductions #(+ (* alpha %2) (* (- 1 alpha) %1)) (first lst) (rest lst)))
(defn exponential-moving-average [lst alpha] (exponentially-weighted-average lst alpha))