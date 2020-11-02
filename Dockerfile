FROM ubuntu:focal
MAINTAINER RenFei <i@renfei.net>

RUN set -ex; \
	apt-get update; \
	if ! which openjdk-8-jdk; then \
		apt-get install -y --no-install-recommends openjdk-8-jdk; \
	fi; \
	if ! which dnsutils; then \
    	apt-get install -y --no-install-recommends dnsutils; \
    fi; \
    if ! which ntp; then \
            apt-get install -y --no-install-recommends ntp; \
    fi; \
    if ! which whois; then \
        apt-get install -y --no-install-recommends whois; \
    fi; \
    rm -rf /var/lib/apt/lists/*

RUN mkdir -p /opt/RenFeiNet/log;

COPY target/RENFEI.NET.jar /app/

HEALTHCHECK --start-period=30s --interval=5m --timeout=10s CMD curl -f http://127.0.0.1:8099/ || exit 1

ENTRYPOINT ["java","-Xms128M","-Xmx512M","-XX:+UseCompressedOops","-XX:+UseConcMarkSweepGC","-XX:SoftRefLRUPolicyMSPerMB=50","-Dfile.encoding=UTF-8","-Xverify:none","-Ddruid.mysql.usePingMethod=false","-jar","/app/RENFEI.NET.jar"]

EXPOSE 8099