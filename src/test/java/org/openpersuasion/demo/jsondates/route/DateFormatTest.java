package org.openpersuasion.demo.jsondates.route;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.openpersuasion.demo.jsondates.model.GsonAnnotatedPojo;
import org.openpersuasion.demo.jsondates.model.JaxbAnnotatedPojo;
import org.openpersuasion.demo.jsondates.model.UnannotatedPojo;

public class DateFormatTest extends CamelTestSupport {

    @Produce(uri = "direct:jackson.unannotated.in")
    protected ProducerTemplate jacksonUnannotatedIn;

    @Produce(uri = "direct:gson.unannotated.in")
    protected ProducerTemplate gsonUnannotatedIn;

    @Produce(uri = "direct:jackson.jaxbannotated.in")
    protected ProducerTemplate jacksonJaxbAnnotatedIn;

    @Produce(uri = "direct:gson.jaxbannotated.in")
    protected ProducerTemplate gsonJaxbAnnotatedIn;

    @Produce(uri = "direct:jackson.gsonannotated.in")
    protected ProducerTemplate jacksonGsonAnnotatedIn;

    @Produce(uri = "direct:gson.gsonannotated.in")
    protected ProducerTemplate gsonGsonAnnotatedIn;

    @EndpointInject(uri = "mock:out")
    private MockEndpoint out;

    @Test
    public void testJacksonMarshallerWithNoAnnotations() throws Exception {

        String messageIn  = "{\"birthDate\":\"1986-12-26\"}";
        String messageOut = "{\"birthDate\":535939200000}";

        out.expectedBodiesReceived(messageOut);
        jacksonUnannotatedIn.sendBody(messageIn);
        out.assertIsSatisfied();
    }

    @Test
    public void testGsonMarshallerWithNoAnnotations() throws Exception {

        String messageIn  = "{\"birthDate\":\"1986-12-26\"}";
        String messageOut = "{\"birthDate\":\"Dec 26, 1986 12:00:00 AM\"}";

        out.expectedBodiesReceived(messageOut);
        gsonUnannotatedIn.sendBody(messageIn);
        out.assertIsSatisfied();

    }

    @Test
    public void testJacksonMarshallerWithJaxbAnnotations() throws Exception {

        String messageIn  = "{\"birthDate\":\"1986-12-26\"}";
        String messageOut = "{\"birthDate\":\"1986-12-26\"}";

        out.expectedBodiesReceived(messageOut);
        jacksonJaxbAnnotatedIn.sendBody(messageIn);
        out.assertIsSatisfied();
    }

    @Test
    public void testGsonMarshallerWithJaxbAnnotations() throws Exception {

        String messageIn  = "{\"birthDate\":\"1986-12-26\"}";
        String messageOut = "{\"birthDate\":\"Dec 26, 1986 12:00:00 AM\"}";

        out.expectedBodiesReceived(messageOut);
        gsonJaxbAnnotatedIn.sendBody(messageIn);
        out.assertIsSatisfied();

    }

    @Test
    public void testJacksonMarshallerWithGsonAnnotations() throws Exception {

        String messageIn  = "{\"birthDate\":\"1986-12-26\"}";
        String messageOut = "{\"birthDate\":535939200000}";

        out.expectedBodiesReceived(messageOut);
        jacksonGsonAnnotatedIn.sendBody(messageIn);
        out.assertIsSatisfied();
    }

    @Test
    public void testGsonMarshallerWithGsonAnnotations() throws Exception {

        String messageIn  = "{\"birthDate\":\"1986-12-26\"}";
        String messageOut = "{\"birthDate\":\"1986-12-26\"}";

        out.expectedBodiesReceived(messageOut);
        gsonGsonAnnotatedIn.sendBody(messageIn);
        out.assertIsSatisfied();

    }

    @Override
    protected RouteBuilder createRouteBuilder(){
        return new RouteBuilder() {
            public void configure() {

                // Jackson with an unannotated pojo
                from("direct:jackson.unannotated.in")
                        .unmarshal().json(JsonLibrary.Jackson, UnannotatedPojo.class)
                        .marshal().json(JsonLibrary.Jackson, UnannotatedPojo.class)
                        .to("mock:out");

                // Gson with an unannotated pojo
                from("direct:gson.unannotated.in")
                        .unmarshal().json(JsonLibrary.Gson, UnannotatedPojo.class)
                        .marshal().json(JsonLibrary.Gson, UnannotatedPojo.class)
                        .to("mock:out");

                // Jackson with a Jaxb-annotated pojo
                from("direct:jackson.jaxbannotated.in")
                        .unmarshal().json(JsonLibrary.Jackson, JaxbAnnotatedPojo.class)
                        .marshal().json(JsonLibrary.Jackson, JaxbAnnotatedPojo.class)
                        .to("mock:out");

                // Gson with a Jaxb-annotated pojo
                from("direct:gson.jaxbannotated.in")
                        .unmarshal().json(JsonLibrary.Gson, JaxbAnnotatedPojo.class)
                        .marshal().json(JsonLibrary.Gson, JaxbAnnotatedPojo.class)
                        .to("mock:out");

                // Jackson with a Gson-annotated pojo
                from("direct:jackson.gsonannotated.in")
                        .unmarshal().json(JsonLibrary.Jackson, GsonAnnotatedPojo.class)
                        .marshal().json(JsonLibrary.Jackson, GsonAnnotatedPojo.class)
                        .to("mock:out");

                // Gson with a Gson-annotated pojo
                from("direct:gson.gsonannotated.in")
                        .unmarshal().json(JsonLibrary.Gson, GsonAnnotatedPojo.class)
                        .marshal().json(JsonLibrary.Gson, GsonAnnotatedPojo.class)
                        .to("mock:out");

            }
        };
    }

}
