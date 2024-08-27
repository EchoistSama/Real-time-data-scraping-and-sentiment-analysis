# # script.py
#
# import pandas as pd
# import requests
# import re
#
# def process_data(selected):
#     # Your existing process_data function code here
#     # Example implementation
#     twitter_data2 = []
#
#     payload2 = {
#         'api_key': 'your_api_key',
#         'query': selected,
#         'num': '1000'
#     }
#
#     try:
#         response = requests.get('http://api.scraperapi.com/structured/twitter/search', params=payload2)
#         response.raise_for_status()
#         data2 = response.json()
#
#         all_tweets2 = data2['organic_results']
#         for tweet in all_tweets2:
#             twitter_data2.append(tweet)
#
#         dfNew = pd.DataFrame(twitter_data2)
#         column_to_filterin = 'snippet'
#
#         df_filtered2 = dfNew[dfNew[column_to_filterin].str.contains(selected, na=False)]
#
#         def keep_only_letters(text):
#             return re.sub(r'[^a-zA-Z\s]', '', text)
#
#         df_filtered2[column_to_filterin] = df_filtered2[column_to_filterin].apply(keep_only_letters)
#         tweet_list = df_filtered2[column_to_filterin].tolist()
#
#         return tweet_list
#
#     except requests.exceptions.RequestException as e:
#         return [f"Error: {e}"]
#
# # Example usage if script.py is run independently
# if __name__ == "__main__":
#     result = process_data("your_selected_item")
#     print("Processed Data:", result)
