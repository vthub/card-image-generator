/*
 * MIT License / Copyright (c) 2017 Vasilii Trofimchuk
 */

package vthub.cardgen;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import ratpack.handling.Context;
import ratpack.http.Request;
import ratpack.path.PathTokens;
import ratpack.util.internal.ImmutableDelegatingMultiValueMap;
import vthub.cardgen.model.Card;
import vthub.cardgen.model.CardBuilder;
import vthub.cardgen.model.Specification;
import vthub.cardgen.model.SpecificationBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CardHandlerTest
{

    @Mock
    private CardService cardService;

    private CardHandler handler;

    @Before
    public void setUp() throws Exception
    {
        initMocks(this);
        handler = spy(new CardHandler(cardService));
    }

    @Test
    public void testHandle() throws Exception
    {
        Card card = CardBuilder.aCard().build();
        doReturn(card).when(handler).createCard(any());

        Specification specification = SpecificationBuilder.aSpecification().build();
        doReturn(specification).when(handler).createSpecification(any());

        Context context = mock(Context.class);
        doNothing().when(context).render(any());

        handler.handle(context);

        ArgumentCaptor<Card> cardCaptor = ArgumentCaptor.forClass(Card.class);
        ArgumentCaptor<Specification> specCaptor = ArgumentCaptor.forClass(Specification.class);

        verify(cardService).generate(cardCaptor.capture(), specCaptor.capture());
        verify(context).render(any());

        assertThat(cardCaptor.getValue(), is(card));
        assertThat(specCaptor.getValue(), is(specification));
    }

    @Test
    public void testGetCard() throws Exception
    {
        doReturn("123").when(handler).getNumber(any());
        doReturn("11/22").when(handler).getExpiry(any());
        doReturn("Test Test").when(handler).getCardHolder(any());

        Card card = handler.createCard(mock(Context.class));

        assertThat(card.getNumber(), is("123"));
        assertThat(card.getExpiry(), is("11/22"));
        assertThat(card.getCardHolder(), is("Test Test"));
    }

    @Test
    public void testCreateSpecification() throws Exception
    {
        doReturn(100).when(handler).getWidth(any());

        Specification specification = handler.createSpecification(mock(Context.class));

        assertThat(specification.getWidth(), is(100));
    }

    @Test
    public void testGetNumber_NotProvided() throws Exception
    {
        String number = handler.getNumber(contextWithPathParam("card", null));
        assertThat(number, is(notNullValue()));
        assertThat(number.length(), is(16));
    }

    @Test
    public void testGetNumber_Empty() throws Exception
    {
        String number = handler.getNumber(contextWithPathParam("card", ""));
        assertThat(number, is(notNullValue()));
        assertThat(number.length(), is(16));
    }

    @Test
    public void testGetNumber_Valid() throws Exception
    {
        String number = handler.getNumber(contextWithPathParam("card", "123567"));
        assertThat(number, is("123567"));
    }

    @Test
    public void testGetNumber_Invalid() throws Exception
    {
        String number = handler.getNumber(contextWithPathParam("card", "abc123123"));
        assertThat(number, is(notNullValue()));
        assertThat(number.length(), is(16));
    }

    @Test
    public void testGetExpiry() throws Exception
    {
        assertThat(handler.getExpiry(contextWithQueryParam("expiry", null)), both(not(isEmptyOrNullString())).and(containsString("/")));
        assertThat(handler.getExpiry(contextWithQueryParam("expiry", "")), both(not(isEmptyOrNullString())).and(containsString("/")));

        assertThat(handler.getExpiry(contextWithQueryParam("expiry", "11/12")), is("11/12"));
        assertThat(handler.getExpiry(contextWithQueryParam("expiry", "13/12")), both(not(isEmptyOrNullString())).and(containsString("/")));
    }

    @Test
    public void testGetCardHolder() throws Exception
    {
        assertThat(handler.getCardHolder(contextWithQueryParam("name", "John")), containsString("JOHN LASTNAME"));
        assertThat(handler.getCardHolder(contextWithQueryParam("name", "")), containsString("LASTNAME"));
        assertThat(handler.getCardHolder(contextWithQueryParam("name", null)), containsString("LASTNAME"));
        assertThat(handler.getCardHolder(contextWithQueryParam("lastname", "Smith")), containsString("NAME SMITH"));
        assertThat(handler.getCardHolder(contextWithQueryParam("lastname", "")), containsString("NAME"));
        assertThat(handler.getCardHolder(contextWithQueryParam("lastname", null)), containsString("NAME"));
        assertThat(handler.getCardHolder(contextWithQueryParam("none", "")), containsString("NAME LASTNAME"));
    }

    @Test
    public void testGetWidth() throws Exception
    {
        assertThat(handler.getWidth(contextWithQueryParam("", "")), is(500));
        assertThat(handler.getWidth(contextWithQueryParam("width", null)), is(500));
        assertThat(handler.getWidth(contextWithQueryParam("width", "")), is(500));
        assertThat(handler.getWidth(contextWithQueryParam("width", "abc")), is(500));
        assertThat(handler.getWidth(contextWithQueryParam("width", "1")), is(1));
    }

    public Context contextWithPathParam(String key, String value)
    {
        Context context = mock(Context.class);
        PathTokens pathTokens = mock(PathTokens.class);
        when(context.getPathTokens()).thenReturn(pathTokens);
        when(pathTokens.get(key)).thenReturn(value);
        return context;
    }

    public Context contextWithQueryParam(String key, String value)
    {
        Context context = mock(Context.class);
        Request request = mock(Request.class);
        when(context.getRequest()).thenReturn(request);
        HashMap<String, List<String>> map = new HashMap<>();
        map.put(key, Collections.singletonList(value));
        when(request.getQueryParams()).thenReturn(new ImmutableDelegatingMultiValueMap<>(map));
        return context;
    }
}
