# HttpParser
HttpParser application

PROBLEM DESCRIPTION:

Write an HTTP parser to parse an HTTP response status line and headers.
● The detailed HTTP message format is described here:
https://datatracker.ietf.org/doc/html/rfc7230#section-3; but for the purpose of this parser,
we will assume a simplified format as described below:
○ Each line in the HTTP response should be terminated with a CR LF sequence
○ The HTTP response will begin with a status line, followed by zero or more header
lines
○ A status line consists of the HTTP version, status code, and reason phrase, each
separated by a space
■ The version is the case-sensitive string “HTTP/” followed by a major and
minor version (e.g. HTTP/1.1)
■ The status code is a 3 digit numeric code
■ The reason phrase is a string describing the status code
○ A header line consists of a header name, followed by “: “, followed by the header
value
■ A header name can contain any letter, digit, and the character “-”
■ A header value can contain any visible/printable ASCII character
● The input would be a contiguous buffer of data with the header part of an HTTP
response. The parser should extract the HTTP version and status code from the status
line, and parse each header line to determine if the header is valid according to the rules
described above.
● The parser should store the valid headers, and provide a way to look up a header value
by name.
● The parser should output the HTTP version and status code, as well as the number of
valid and invalid headers. If the response does not have a valid status line, then the
parser can output an error message and abort parsing.
