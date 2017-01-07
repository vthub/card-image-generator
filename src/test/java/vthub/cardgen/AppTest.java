package vthub.cardgen;

import org.junit.After;
import org.junit.Test;
import ratpack.http.client.ReceivedResponse;
import ratpack.test.MainClassApplicationUnderTest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AppTest
{

    private final MainClassApplicationUnderTest aut = new MainClassApplicationUnderTest(App.class);

    @After
    public void tearDown()
    {
        aut.close();
    }

    @Test
    public void cardHandler()
    {
        assertThat(get("foo").getStatusCode(), is(200));
    }

    private ReceivedResponse get(String path)
    {
        return aut.getHttpClient().get(path);
    }

}
