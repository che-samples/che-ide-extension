# Che IDE Extension Sample

## Build

You can build this sample using command `mvn clean install`, as well as using Docker Image `eclipse/che-dev:nightly` to build  

Description of mounting volumes 
- `/home/user/.m2` - use this to mount your local Maven repository
- `/projects` - mount for the sample directory

Example of running this command
`docker run -ti -v ~/.m2:/home/user/.m2 -v /home/user/che-ide-extension/:/projects eclipse/che-dev:nightly sh -c "mvn clean install"`

Note, that building the module with GWT compilation may fail, if there would not be enough memory.
**Make sure that your Docker container can use at least 3GB of RAM.**

# Run

Run this sample by mounting assembly to your Che Docker image:

Description of mounted volumes:
- `/var/run/docker.sock` - docker socket, required by Che for launching workspaces
- `/data` - path to Che data files on host system(logs, configuration)
- `/assembly` - path to your Che assembly-main files location (note that it has to point to the unwrapped Che files in `target/<eclipse-che-version>/<eclipse-che-version>` of assembly-main)

Example of running this command
`docker run -it --rm -v /home/user/che-data/:/data -v /home/user/che-samples/che-ide-extension/assembly/assembly-main/target/eclipse-che-6.13.0/eclipse-che-6.13.0:/assembly -v /var/run/docker.sock:/var/run/docker.sock eclipse/che:nightly start --skip:scripts`
