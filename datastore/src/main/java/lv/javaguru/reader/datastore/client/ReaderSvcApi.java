package lv.javaguru.reader.datastore.client;

import lv.javaguru.reader.datastore.domain.Feed;
import retrofit.http.GET;
import retrofit.http.Path;

import java.util.Collection;

public interface ReaderSvcApi {

	@GET("/feeds")
	public Collection<Feed> getFeedList();

	@GET("/feeds/{id}")
	public Feed getFeedById(@Path("id") long id);

}
