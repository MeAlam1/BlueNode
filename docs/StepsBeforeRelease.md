# Steps Before Release

- **1. Update Gradle Version**:
    - Update the `version` property in `gradle.properties` to match the new release version.
    - Ensure all dependencies are up-to-date in `build.gradle`.

- **2. Update Constants**:
    - Set the version constant in your `Constants` file to the new version.
    - Ensure `DevMode` boolean in `Constants` is set to `false` for release.

- **3. Run Unit Tests**:
    - Execute all unit tests to ensure no regressions or issues.
    - Address any failing tests before proceeding.

- **4. Run Integration Tests**:
    - If you have integration tests, execute them as well to verify functionality across modules.

- **5. Check Code Quality**:
    - Run `./gradlew spotlessCheck` to ensure code formatting is correct.
    - Run Qodana to check for code quality issues.
    - Address any warnings or errors.

- **6. Update Documentation**:
    - Update `README`, `CHANGELOG`, and any additional user-facing documentation.
    - Ensure release notes are included in the CHANGELOG.

- **7. Build the App**:
    - Execute the Gradle build task to build the application (`./gradlew build`).
    - Verify the output artifacts are correctly built.

- **8. Generate JavaDocs**:
    - Run the Gradle task to generate JavaDocs (`./gradlew javadoc`).
    - Review the generated documentation for accuracy and completeness.

- **9. Perform Manual Testing**:
    - Test the application manually to check for any unexpected issues or bugs.

- **Verify Distribution Artifacts**:
    - Ensure all necessary files (e.g., JAR, WAR, etc.) are included in the build output.

- **Prepare Release Tags**:
    - Create a Git tag for the release (e.g., `v1.0.0`).
    - Push the tag to the repository.

- **Publish the App**:
    - Publish the application to the relevant platform.
    - Verify successful upload and accessibility.

- **Post-Release Tasks**:
    - Update `gradle.properties` with the next snapshot version.
    - Update `DevMode` boolean in `Constants` back to `true` if needed for development.
    - Announce the release in relevant channels.
