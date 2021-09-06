(ns growth-plan-report.core
  (:require [clj-http.client :as client])
  (:require [clojure.data.json :as json])
  (:require [clojure.data.csv :as csv])
  (:require [clojure.java.io :as io]))

(def api-url "https://api.hibob.com/v1/docs/people/")
(def api-token (slurp "resources/bob-api-key"))
(def growth-plan-folder-id 599875) ;; magical bob growth plans shared folder id
(def input-file "resources/input.csv")
(def output-file "resources/output.csv")

(defn call-api [url id secret]
  (client/get (str url id) {:headers {:Accept "application/json" :Authorization secret}}))

(defn get-json [id]
  (json/read-str (:body (call-api api-url id api-token)) :key-fn keyword))

(defn get-folder-id [document] (get-in document [:doc :folderId]))

(defn select-growth-plans [documents]
  (select-keys (get (first (filter (fn [x]
                                     (= (get-folder-id x) growth-plan-folder-id))
                                     (get documents :documents))) :doc) [:name]))

(defn process-row [row]
  (let [id (first row)]
    (conj row (get (select-growth-plans (get-json id)) :name))))

(defn run [from to]
  (with-open [reader (io/reader from)
              writer (io/writer to :append false)]
    (->> (csv/read-csv reader)
         (map process-row)
         (csv/write-csv writer))))

(defn -main [] (run input-file output-file))