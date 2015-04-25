package assign2;

import java.util.Iterator;

import assign2.exceptions.DatabaseException;

public class MovieDatabase {
	MyLinkedList<Genre> genres;
	
	public MovieDatabase() {
		// FIXME implement this
		// Maintain a linked list of Genre using MyLinkedList
		genres=new MyLinkedList<Genre>();
	}

	public void insert(String genre, String title) {
		// FIXME implement this
		// Insert the given genre and title to the MovieDatabase.
		// Printing functionality is provided for the sake of debugging.
		// This code should be removed before submitting your work.
		
		Genre newGenre = new Genre(genre);
		boolean flag = genres.add(newGenre);
		if(flag){
			newGenre.getMovies().add(title);
		} else {
			Iterator<Genre> itr =genres.iterator();
			while(itr.hasNext()){
				Genre thisGenre = itr.next();
				if(genre.equals(thisGenre.getName())){
					thisGenre.getMovies().add(title);
				}
			}
			
		}
	}

	public void delete(String genre, String title) throws DatabaseException {
		// FIXME implement this
		// Remove the given genre and title from the MovieDatabase.
		// Printing functionality is provided for the sake of debugging.
		// This code should be removed before submitting your work.
		
		Iterator<Genre> genreItr = genres.iterator();
		while(genreItr.hasNext()){
			Genre thisGenre = genreItr.next();
			if(genre.equals(thisGenre.getName())){
				MyLinkedList<String> movies = thisGenre.getMovies();
				movies.remove(title);
				if(movies.size()==0){
					genres.remove(thisGenre);
				}
			}
		}
	}

	public MyLinkedList<QueryResult> search(String term) {
		// FIXME implement this
		// Search the given term from the MovieDatabase.
		// You should return a linked list of QueryResult.
		// The search command is handled at SearchCmd.java.
		// Printing functionality is provided for the sake of debugging.
		// This code should be removed before submitting your work.

		MyLinkedList<QueryResult> results = new MyLinkedList<QueryResult>();
		Iterator<Genre> genreItr = genres.iterator();
		while(genreItr.hasNext()){
			Genre thisGenre = genreItr.next();
			Iterator<String> movieItr = thisGenre.getMovies().iterator();
			while(movieItr.hasNext()){
				String movie = movieItr.next();
				if(movie.contains(term==null?"":term)){
					results.add(new QueryResult(thisGenre.getName(), movie));
				}
			}
		}
		
		return results;
	}
}
