package simple;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import simple.resources.SimpleServiceResource;

public class SimpleServiceApplication extends Application<SimpleServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SimpleServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "simple-service";
    }

    @Override
    public void initialize(final Bootstrap<SimpleServiceConfiguration> bootstrap) {
    }

    @Override
    public void run(final SimpleServiceConfiguration configuration,
                    final Environment environment) {
        final SimpleServiceResource resource = new SimpleServiceResource();
        environment.jersey().register(resource);
    }

}
