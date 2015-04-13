package assign2;
public class QueryResult implements Comparable<QueryResult> {

	private final String genre;
	private final String title;

	public QueryResult(String genre, String title) {
		if (genre == null) throw new NullPointerException("genre");
		if (title == null) throw new NullPointerException("title");

		this.genre = genre;
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public int compareTo(QueryResult other) {
		// TODO delete the line below and implement this method
		int genreCompare = this.genre.compareTo(other.genre);
		if(genreCompare==0)
			return this.title.compareTo(other.title);
		return this.genre.compareTo(other.genre);
					
		
//		throw new UnsupportedOperationException();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QueryResult other = (QueryResult) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

}
