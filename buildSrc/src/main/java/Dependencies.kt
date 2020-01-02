object Versions {
    const val kotlin = "1.3.61"
}

object BuildLibs {
    const val gradle = "com.android.tools.build:gradle:3.5.0"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val ktlint = "org.jlleitschuh.gradle:ktlint-gradle:9.1.1"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val coreKtx = "androidx.core:core-ktx:1.1.0"

    const val roboelectric = "org.robolectric:robolectric:4.3.1"

    // Assertions
    const val junit = "androidx.test.ext:junit:1.1.1"

    // AndroidJUnitRunner
    const val runner = "androidx.test:runner:1.1.0"
}
