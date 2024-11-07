# flow-log-analyzer
Illumio Assessment Project

- Parser com/illumio/parser package has the FLowLogParser interface which is implemented by V2FlowLogParser, if flow line is version 2 then this implementation has to be used otherwise a new implementation should be created.
- The parser use regex to determine if the line conforms to version 2 otherwise ignores that line
- Parser will return a collection of V2FlowLogLine which is a java object and all analysis is based on this object
- The required counting is done using CountingStrategy interface which has method to accept a collection of a single line to analyze and store the result
- There are various Strategy classes but the required ones are SourcePortProtocolStrategy and V2TagCountStrategy
- The results can be published to any output - file, stdio etc as the OutputFormatter accepts a Writer to write the results to it.
- We can have various other filters and direct the output of these filters to any channel (writer)
- We can easily write other counting strategies and easily plugin those strategies to this implementation.
- Also there are various test cases part of the suite which tests various scenarios for sanity only
- Though only version 2 is supported but it is easily extendable to other versions
- Finally, please look at Main class for usage and output.