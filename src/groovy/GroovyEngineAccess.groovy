import engine.loop.groovy.GroovyEngine;

class GroovyEngineAccess {

	private GroovyEngine engine;

	def GroovyEngineAccess(GroovyEngine javaEngine) {
		engine = javaEngine;
	}

	def Object with() {
		return engine.with();
	}

	def Object with(Object obj) {
		return engine.with(obj);
	}

	def end() {
		engine.end();
	}

	def Object current() {
		return engine.current();
	}
}