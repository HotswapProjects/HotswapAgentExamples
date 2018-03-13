FROM hotswapagent/tomcat
RUN mkdir -p /extra_class_path
RUN mkdir -p /webapp_dir
COPY ./target/deltaspike-hibernate-jsf-0.0.1-SNAPSHOT.war ${DEPLOYMENT_DIR}
ENV JAVA_OPTS="-XXaltjvm=dcevm -agentlib:jdwp=transport=dt_socket,server=y,address=8000,suspend=n -javaagent:/opt/hotswap-agent/hotswap-agent.jar -Dextra.class.path=/extra_class_path -Dwebapp.dir=/webapp_dir"
