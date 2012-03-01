(defproject smoothing "1.0.0-SNAPSHOT"
  :description "FIXME: write"
  :dependencies [[org.clojure/clojure "1.3.0-beta1"]
                 [org.clojure/algo.generic "0.1.0"]]

  :plugins [[s3-wagon-private "1.1.1"]]

  :repositories {"nfr-releases" "s3p://newfound-mvn-repo/releases/"
                 "nfr-snapshots" "s3p://newfound-mvn-repo/snapshots/"})
