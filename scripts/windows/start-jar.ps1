    [CmdletBinding()]
    param (
        [Parameter(Mandatory = $true)]
        [Int16]$port
    )

    $files = Get-ChildItem -Path ./build/libs -Exclude "*stubs*"
    $service_jar_file = $files.Name

    Write-Host "Starting $service_jar_file on port $port with production-profile activated."
    & java "-Dserver.port=$port" "-Dspring.profiles.active=production" -jar "./build/libs/$service_jar_file"

        