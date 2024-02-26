# Function to recursively delete folders named 'target'
function Remove-TargetFolders {
    param (
        [string]$path
    )

    # Get all 'target' folders in the current directory
    $targetFolders = Get-ChildItem -Path $path -Filter 'target' -Directory -Recurse

    # Iterate through each 'target' folder and remove it
    foreach ($folder in $targetFolders) {
        # Remove the folder and all its contents
        Remove-Item -Path $folder.FullName -Recurse -Force
        Write-Output "Deleted folder: $($folder.FullName)"
    }
}

# Call the function to remove 'target' folders in the current directory and its subdirectories
Remove-TargetFolders -path ".\"
