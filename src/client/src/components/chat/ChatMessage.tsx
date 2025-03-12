import { useState, memo } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faAngleDown, faAngleUp } from '@fortawesome/free-solid-svg-icons';
import { ChatMessage as ChatMessageType } from '../../types/chat';
import { useTypingEffect } from '../../hooks/useTypingEffect';
import ReactMarkdown from 'react-markdown';
import viteLogo from '/vite.svg';

interface ChatMessageProps {
  message: ChatMessageType;
  isLoading?: boolean;
}

const ChatMessage = memo(({ message, isLoading = false }: ChatMessageProps) => {
  const [collapsed, setCollapsed] = useState(false);
  const isUser = message.role === 'user';
  const typingDots = useTypingEffect(isLoading && !isUser);
  
  const formatThinkingTime = (ms?: number) => {
    if (!ms) return '';
    if (ms < 1000) return `${ms}ms`;
    return `${(ms / 1000).toFixed(1)} second${ms >= 2000 ? 's' : ''}`;
  };
  
  return (
    <div className={`flex ${isUser ? "justify-end" : "justify-start"} p-4`}>
      <div className={`p-3 px-4 max-w-[95%] ${isUser ? "bg-blue-100 rounded-full" : ""}`}>
        {message.role === "assistant" && (
          <>
            <div className='flex items-center mb-4'>
              <div className="avatar p-2">
                <img src={viteLogo} className="w-8 h-8" alt="Vite logo" />
              </div>
              {message.think && message.think !== "\n\n" && (
                <div className="thinking">
                  <button type='button'
                    onClick={() => setCollapsed(c => !c)}
                    className="text-blue-700 hover:bg-blue-50 font-medium rounded-lg text-sm p-2 inline-flex items-center cursor-pointer">
                    <span className={message.thinking ? "animate-pulse" : ""}>
                      {message.thinkingTime 
                        ? `Thought for ${formatThinkingTime(message.thinkingTime)}` 
                        : `Thinking${typingDots}`}
                    </span>
                    <FontAwesomeIcon
                      icon={collapsed ? faAngleDown : faAngleUp}
                      className='ml-2'
                    />
                  </button>
                </div>
              )}
            </div>
            {message.think && message.think !== "\n\n" && (
              <div className={`${collapsed ? "hidden" : "block"} bg-blue-100 mb-4 text-sm italic border-l-2 border-gray-400 pl-2 py-1`}>
                <ReactMarkdown>{message.think}</ReactMarkdown>
              </div>
            )}
          </>
        )}

        <article className="prose max-w-none">
          <ReactMarkdown>{message.content}</ReactMarkdown>
          {isLoading && !isUser && message.content === '' && (
            <span className="inline-block animate-pulse">Thinking{typingDots}</span>
          )}
        </article>

        {message.role === "assistant" && isLoading && (
          <div role="status" className="mt-2">
            <svg aria-hidden="true" className="inline w-6 h-6 text-gray-200 animate-spin dark:text-gray-600 fill-blue-600" viewBox="0 0 100 101" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z" fill="currentColor" />
              <path d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z" fill="currentFill" />
            </svg>
            <span className="sr-only">Loading...</span>
          </div>
        )}
      </div>
    </div>
  );
});

export default ChatMessage;
