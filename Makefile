compile:
	@echo "Compiling..."
	@find * -name "*.java" > sources.txt
	@javac -source 1.7 -target 1.7 -Xlint:-options -d bin @sources.txt

run: compile
	@echo "Running..."
	@cd bin && java com.slopez.avaj.Main ../scenario.txt "e6e219eddaae29b43f1e5b6960ce80e9"
	@echo "=== simulation.txt ==="
	@cat -e bin/simulation.txt
