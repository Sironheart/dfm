package dev.beisenherz.dfm

import com.github.ajalt.clikt.completion.CompletionCommand
import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.core.subcommands

fun main(args: Array<String>) = Main()
    .subcommands(
        CompletionCommand(name = "completion")
    )
    .main(args)

private class Main : NoOpCliktCommand(name = "dfm", printHelpOnEmptyArgs = true)
