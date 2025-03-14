import numpy as np

class QLearningAgent:
    def __init__(self, states, actions, learning_rate=0.1, discount_factor=0.9):
        self.q_table = np.zeros((states, actions))
        self.lr = learning_rate
        self.gamma = discount_factor

    def update(self, state, action, reward, next_state):
        current_q = self.q_table[state, action]
        next_max_q = np.max(self.q_table[next_state])
        new_q = current_q + self.lr * (reward + self.gamma * next_max_q - current_q)
        self.q_table[state, action] = new_q

    def get_action(self, state):
        return np.argmax(self.q_table[state])

# 使用示例
agent = QLearningAgent(states=10, actions=4)
agent.update(state=0, action=1, reward=5, next_state=1)