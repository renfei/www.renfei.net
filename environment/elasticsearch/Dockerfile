FROM elasticsearch:7.6.2
MAINTAINER RenFei <i@renfei.net>

RUN cd /usr/share/elasticsearch/bin
RUN elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.6.2/elasticsearch-analysis-ik-7.6.2.zip

EXPOSE 9200,9300