package demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.cloud.stream.reactive.FluxSender
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux

@SpringBootApplication
class ZipkinReactiveProblemApplication

fun main(args: Array<String>) {
    runApplication<ZipkinReactiveProblemApplication>(*args)
}

@EnableBinding(Processor::class)
@Configuration
class ZipkinReactiveProblemConfiguration {

    @StreamListener
    fun streamHandler(@Input(Processor.INPUT) input: Flux<String>,
                @Output(Processor.OUTPUT) output: FluxSender) {
        output.send(input)
    }
}

