# Stage 1: build com Maven + JDK
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /workspace
# copia arquivos do maven
COPY pom.xml .
COPY src ./src
# build sem testes para acelerar (mude se quiser)
RUN mvn -B -DskipTests package

# Stage 2: runtime com JRE leve
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# porta que sua app usa (Render usa PORT env, mas expor 8080 é ok)
EXPOSE 8080

# copia jar gerado (ajuste o nome conforme o resultado em target/)
COPY --from=build /workspace/target/*.jar app.jar

# entrypoint
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
