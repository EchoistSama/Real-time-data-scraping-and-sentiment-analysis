import pandas as pd
import requests
import re
user_input = None

def set_input(input_text):
    global user_input
    user_input = input_text
    hashtag = "#"
    topic_interest = hashtag + user_input

    twitter_data = []

    payload = {
            'api_key' : 'eb9748ca4e45915847970971d3724907',    #eb9748ca4e45915847970971d3724907 | 4ad05b56ac3328c3397b926745d81dd3 | fdb6326fd09372cccbf86db5f2a0fc0b
            'query' : topic_interest,
         'num' : '20'
        }

    response = requests.get('https://api.scraperapi.com/structured/twitter/search', params=payload)

    data = response.json()

    all_tweets = data['organic_results']
    for tweet in all_tweets:
        twitter_data.append(tweet)

    df = pd.DataFrame(twitter_data)
    tweets = df['snippet']

        # Select the specific column (e.g., column 'A')
    column_to_filter = 'snippet'

        # Filter rows that contain '#'
    df_filtered = df[df[column_to_filter].str.contains('#', na=False)]

        # Extract text starting with '#' and the text that follows it
    df_filtered[column_to_filter] = df_filtered[column_to_filter].str.extract(r'(#[\w]*)', expand=False)

        # Keep only the filtered column
    df_filtered = df_filtered[[column_to_filter]]
    items_list  = df_filtered['snippet'].tolist()
    return items_list

def process_data(selected):
    twitter_data2 = []

    payload2 = {
        'api_key': 'eb9748ca4e45915847970971d3724907',
        'query': selected,
        'num': '100'
    }
    try:
        response = requests.get('https://api.scraperapi.com/structured/twitter/search', params=payload2)
        response.raise_for_status()
        data2 = response.json()

        all_tweets2 = data2['organic_results']
        for tweet in all_tweets2:
            twitter_data2.append(tweet)

        dfNew = pd.DataFrame(twitter_data2)
        column_to_filterin = 'snippet'

        df_filtered2 = dfNew[dfNew[column_to_filterin].str.contains(selected, na=False)]

        def keep_only_letters(text):
            return re.sub(r'[^a-zA-Z\s]', '', text)

        df_filtered2[column_to_filterin] = df_filtered2[column_to_filterin].apply(keep_only_letters)
        tweet_list = df_filtered2[column_to_filterin].tolist()

        return tweet_list

    except requests.exceptions.RequestException as e:
        return [f"Error: {e}"]
# Example usage if script.py is run independently
if __name__ == "__main__":
    result = process_data("your_selected_item")
    print("Processed Data:", result)


def get_input():
    global user_input
    return user_input

def get_selected_item():
    global selected_item
    return selected_item

