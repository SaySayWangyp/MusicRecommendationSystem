package utils;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import models.DataSet;
import models.Song;

/**
 * Class with basic utility methods.
 *
 */
public class Utility
{
	private static Logger LOG = Logger.getLogger(Utility.class);
	
	/**
	 * Method to get the accuracy of the recommendations returned by different algorithms.
	 * @param recommendation	Map of UserID to the List of recommended songs
	 * @return					Accuracy of the algorithm
	 */
	public static Double getAccuracy(Map<String, List<Song>> recommendations, DataSet testDataset)
	{
		double overallAccuracy = 0.0;
		for(Map.Entry<String, List<Song>> entry : recommendations.entrySet()) {
			String userId = entry.getKey();
			List<Song> predictedSongs = entry.getValue();
			Map<String, Integer> actualSongs = testDataset.getmUserListeningHistory().get(userId); 

			int totalRecommendations = predictedSongs.size();
			int matchedSongs = 0;
			for(Song s: predictedSongs) {
				if(actualSongs.containsKey(s.mSongID)) {
					++matchedSongs;
				}
			}
			
			double accuracyForUser = (matchedSongs)/(double)totalRecommendations;
			LOG.debug("Accuracy for user " + userId + " is " + accuracyForUser + 
					" with " + matchedSongs + " matched songs ");
			
			overallAccuracy += accuracyForUser;
		}
		
		int numUsers = recommendations.keySet().size();
		return (overallAccuracy*100)/numUsers;
	}
	
	/**
	 * Method to calculate Jaccard Similarity Score.
	 * @return	Jaccard Similarity Score
	 */
	public static Double calculateJaccardSimilary()
	{
		return 0.0;
	}
	
	/**
	 * Method to calculate Cosine Distance.
	 * @return	Cosine Distance Value
	 */
	public static Double calculateCosineDistance()
	{
		return 0.0;
	}
}
