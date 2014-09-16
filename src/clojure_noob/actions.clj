(ns clojure-noob.actions)

(defn update-player [{:keys [x y] :as player} key-pressed]
  (case key-pressed
    :up (assoc player :y (max 0 (dec y)))))

