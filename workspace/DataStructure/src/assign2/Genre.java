package assign2;
class Genre implements Comparable<Genre> {
	// FIXME implement this
	// Implement a Genre class.
	// This class should hold the name of the genre.
	// This class should maintain a linked list of movie titles for this genre.
	private String name;
	private MyLinkedList<String> movies;
	
	public Genre(String name) {
		this.name=name;
		movies = new MyLinkedList<String>();
	}

	public String getName() {
		return name;
	}

	public MyLinkedList<String> getMovies() {
		return movies;
	}

	@Override
	public int compareTo(Genre other) {
		// TODO implement this
		return this.name.compareTo(other.name);
//		throw new UnsupportedOperationException();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String toString =  "(" + name ;
		for (String string : movies) {
			toString=toString+ ", "+string;
		}
		toString=toString+")";
		return toString;
	}


}
