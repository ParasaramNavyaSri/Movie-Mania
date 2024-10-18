package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import utils.DatabaseUtils;
import model.Movie;

public class MovieDAO {
	private static final String INSERT_MOVIES_SQL = "INSERT INTO Movies (title, genre, price, show_time, image_url) VALUES (?, ?, ?, ?, ?);";
	private static final String SELECT_MOVIE_BY_ID_SQL = "SELECT * FROM Movies WHERE id = ?;";
	private static final String SELECT_ALL_MOVIES = "SELECT * FROM Movies;";
	private static final String DELETE_MOVIE_SQL = "DELETE FROM Movies WHERE id = ?;";
	private static final String UPDATE_MOVIE_SQL = "UPDATE Movies SET title = ?, genre = ?, price = ?, show_time = ?, image_url = ? WHERE id = ?;";


    public static Optional<Movie> getMovieById(int movieId) {
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_MOVIE_BY_ID_SQL)) {
            ps.setInt(1, movieId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getDouble("price"),
                        rs.getString("show_time"),
                        rs.getString("image_url") // Add this line
                    ));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Consider using a logging framework
        }
        return Optional.empty();
    }

    public static List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MOVIES);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                double price = rs.getDouble("price");
                String showTime = rs.getString("show_time");
                String imageUrl = rs.getString("image_url"); // Add this line
                movies.add(new Movie(id, title, genre, price, showTime, imageUrl)); 
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Consider using a logging framework
        }
        return movies;
    }

    public static void insertMovie(Movie movie) throws ClassNotFoundException {
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MOVIES_SQL)) {
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getGenre());
            preparedStatement.setDouble(3, movie.getPrice());
            preparedStatement.setString(4, movie.getShowTime());
            preparedStatement.setString(5, movie.getImageUrl()); // Add this line
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework
        }
    }

    public static void updateMovie(Movie movie) {
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MOVIE_SQL)) {
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getGenre());
            preparedStatement.setDouble(3, movie.getPrice());
            preparedStatement.setString(4, movie.getShowTime());
            preparedStatement.setString(5, movie.getImageUrl()); // Add this line
            preparedStatement.setInt(6, movie.getId()); // Update index for ID
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Consider using a logging framework
        }
    }

    public static void deleteMovie(int id) throws ClassNotFoundException {
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MOVIE_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework
        }
    }
}
