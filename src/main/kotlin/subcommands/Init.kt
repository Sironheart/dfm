package dev.beisenherz.dfm.subcommands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.validate
import com.github.ajalt.clikt.parameters.options.defaultLazy
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.path
import java.nio.file.Path
import kotlin.io.path.exists

class Init : CliktCommand(
    name = "init",
    printHelpOnEmptyArgs = true,
    help = "Initialize the dotfiles files, using an existing repository."
) {
    val path by option().path(canBeFile = false)
        .defaultLazy { Path.of("${System.getProperty("user.home")}/.dotfiles") }
    val gitRepo by argument().validate {
        require(
            "((http|git|ssh|http(s)|file|\\/?)|(git@[\\w\\.]+))(:(\\/\\/)?)([\\w\\.@\\:/\\-~]+)(\\.git)(\\/)?".toRegex()
                .matches(it)
        )
    }

    override fun run() {
        echo(path.exists())
    }
}