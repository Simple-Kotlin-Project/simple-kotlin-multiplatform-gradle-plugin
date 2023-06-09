package io.github.edmondantes.simple.kotlin.multiplatform.gradle.plugin.organization

import io.github.edmondantes.simple.kotlin.multiplatform.gradle.plugin.util.url.PatterUrlFactory
import io.github.edmondantes.simple.kotlin.multiplatform.gradle.plugin.util.url.UrlFactory
import io.github.edmondantes.simple.kotlin.multiplatform.gradle.plugin.util.url.UrlType

data class SimpleProjectOrganization(val organizationName: String, val urlFactory: UrlFactory) {
    companion object {
        private const val GITHUB_URL = "https://www.github.com"
        private const val SCM_GITHUB_URL = "scm:git:git://github.com"
        val GITHUB = SimpleProjectOrganization("github", PatterUrlFactory().apply {
            forType(UrlType.HTTP) {
                pattern(1, "$GITHUB_URL/{0}")
                pattern(2, "$GITHUB_URL/{0}/{1}")
                pattern(3, "$GITHUB_URL/{0}/{1}/tree/{2}")
                elsePattern(GITHUB_URL)
            }
            forType(UrlType.SCM) {
                pattern(2, "$SCM_GITHUB_URL/{0}/{1}.git")
            }

            forTypeFrom(UrlType.HTTPS, UrlType.HTTP)
            unspecified(UrlType.HTTP)
        })
    }
}