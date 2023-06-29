/**
 * This object defines constants used throughout the project.
 *
 * `LOCAL_PROPERTIES`: The filename for the local properties file, which is typically used to store machine-specific environment variables.
 *
 * `BASE_URL`: A key for the base URL of the API. This is typically used to store the base URL in a properties file or as an environment variable.
 *
 * `EMPTY_STRING`: A constant for an empty string. This can be used whenever an empty string is needed in the code.
 *
 * `BASE_URL_TYPE`: The type of the BASE_URL constant, which is a String.
 *
 * `PROGUARD_ANDROID_OPTIMIZE`: The filename for the ProGuard configuration file. ProGuard is a tool that shrinks, optimizes, and obfuscates your code.
 *
 * `PROGUARD_RULES`: The filename for the ProGuard rules file. This file typically contains rules for how ProGuard should treat your code during the optimization and obfuscation process.
 *
 * `EXCLUDES`: A constant string used to exclude certain files from certain processes. In this case, it's excluding files in the /META-INF/ directory with the names AL2.0 and LGPL2.1.
 */
object Constants {
    const val LOCAL_PROPERTIES = "local.properties"
    const val BASE_URL = "BASE_URL"
    const val EMPTY_STRING = ""
    const val BASE_URL_TYPE = "String"

    const val PROGUARD_ANDROID_OPTIMIZE = "proguard-android-optimize.txt"
    const val PROGUARD_RULES = "proguard-rules.pro"

    const val EXCLUDES = "/META-INF/{AL2.0,LGPL2.1}"
}