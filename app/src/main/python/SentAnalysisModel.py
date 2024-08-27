import re

# Function to load VADER lexicon from the file content string
def load_vader_lexicon_from_string(file_content):
    vader_lexicon = {}
    lines = file_content.splitlines()
    for line in lines:
        if line.strip() and not line.startswith(';'):  # Skip comment lines
            word, score = line.split('\t')[0], float(line.split('\t')[1])
            vader_lexicon[word] = score
    globals()['vader_lexicon'] = vader_lexicon  # Store the lexicon globally

# Function to predict sentiment using VADER lexicon
def predict_sentiment(tweet):
    tweet = preprocess_tweet(tweet)
    sentiment_score = get_sentiment_score(tweet)

    if sentiment_score >= 0.05:
        return "positive"
    elif sentiment_score <= -0.05:
        return "negative"
    else:
        return "neutral"

# Function to preprocess tweet (similar to your preprocess_tweet function)
def preprocess_tweet(tweet):
    tweet = re.sub(r"http\S+", "", tweet)  # Remove URLs
    tweet = re.sub(r"@\w+", "@user", tweet)  # Replace @mentions with "@user"
    return tweet

# Function to calculate sentiment score based on VADER lexicon
def get_sentiment_score(tweet):
    words = tweet.lower().split()
    sentiment_score = 0
    for word in words:
        if word in vader_lexicon:
            sentiment_score += vader_lexicon[word]
    return sentiment_score

# Function to process tweets and predict sentiment
def process_tweets(result):
    for idx, tweet in enumerate(result):
        print(f"Tweet {idx + 1}: {tweet}")
        sentiment = predict_sentiment(tweet)
        print(f"Prediction: It is a {sentiment} tweet\n")

if __name__ == "__main__":
    # Example usage with predefined list of tweets
    result = ["I love this!", "This is terrible.", "I'm not sure how I feel about this."]
    process_tweets(result)

def count_sentiments(result):
    positive_count = 0
    negative_count = 0
    neutral_count = 0

    for tweet in result:
        sentiment = predict_sentiment(tweet)
        if sentiment == "positive":
            positive_count += 1
        elif sentiment == "negative":
            negative_count += 1
        elif sentiment == "neutral":
            neutral_count += 1

    return [positive_count, neutral_count, negative_count]