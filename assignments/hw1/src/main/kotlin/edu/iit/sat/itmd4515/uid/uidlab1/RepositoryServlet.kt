package edu.iit.sat.itmd4515.uid.uidlab1

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.footer
import kotlinx.html.h1
import kotlinx.html.h2
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.script
import kotlinx.html.section
import kotlinx.html.span
import kotlinx.html.stream.appendHTML
import kotlinx.html.title
import kotlinx.html.unsafe
import org.commonmark.ext.gfm.tables.TablesExtension
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import java.io.IOException

/** The servlet to personal repository page. */
@WebServlet(name = "repository", value = ["/repository"])
class RepositoryServlet : HttpServlet() {
    @Throws(IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val repository =
            Repository.valueOf(
                request
                    .getParameter("id")
                    .replace('-', '_')
                    .uppercase(),
            )
        val isDarkMode = request.getParameter("dark-mode")?.toBoolean() ?: false

        response.contentType = "text/html"
        response.writer.appendHTML().html {
            head {
                title(repository.id)

                meta(name = "viewport", content = "width=device-width, initial-scale=1")
                meta(name = "theme-color", content = COLOR_PRIMARY)

                link(rel = "stylesheet", href = "styles/cayman.css")
                when {
                    isDarkMode -> {
                        link(rel = "stylesheet", href = "styles/cayman-dark.css")
                        link(
                            rel = "stylesheet",
                            href = "${PRISM_DIR}themes/prism-tomorrow.min.css",
                        )
                    }
                    else ->
                        link(
                            rel = "stylesheet",
                            href = "${PRISM_DIR}themes/prism.min.css",
                        )
                }
                link(rel = "stylesheet", href = "styles/prism-tomorrow.css")
                link(rel = "stylesheet", href = "styles/normalize.css")

                script(src = "${PRISM_DIR}prism.min.js") { }
                script(src = "${PRISM_DIR}components/prism-gradle.min.js") { }
                script(src = "${PRISM_DIR}components/prism-kotlin.min.js") { }
            }
            body {
                section(classes = "page-header") {
                    h1(classes = "project-name") { text(repository.fullName) }
                    h2(classes = "project-tagline") { text(repository.description) }
                    a(classes = "btn", href = "index.jsp") { text("Return Home") }
                    a(classes = "btn", href = repository.url) { text("View on GitHub") }
                }
                section(classes = "main-content") {
                    unsafe {
                        +RENDERER.render(PARSER.parse(repository.content.readText()))
                    }

                    footer(classes = "site-footer") {
                        span(classes = "site-footer-owner") {
                            a(href = repository.url) { text(repository.id) }
                            text(" is maintained by ")
                            a(href = "http://hanggrian.com") { text("Hendra Wijaya") }
                        }
                        span(classes = "site-footer-credits") {
                            text("Theme by ")
                            a(href = "https://github.com/jasonlong/cayman-theme/") {
                                text("jasonlong")
                            }
                        }
                    }
                }
            }
        }
    }

    override fun destroy() {}

    private companion object {
        const val PRISM_DIR = "https://cdnjs.cloudflare.com/ajax/libs/prism/1.29.0/"
        const val COLOR_PRIMARY = "#159957"

        val RENDERER: HtmlRenderer =
            HtmlRenderer
                .builder()
                .extensions(listOf(TablesExtension.create()))
                .build()

        val PARSER: Parser =
            Parser
                .builder()
                .extensions(listOf(TablesExtension.create()))
                .build()
    }
}
