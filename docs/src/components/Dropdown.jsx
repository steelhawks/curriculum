import React from 'react';

/**
 * A reusable dropdown component for brain teaser solutions.
 * @param {string} code - The code snippet to display.
 * @param {string} explanation - The text explanation of the solution.
 * @param {string} label - Optional: The text on the clickable bar (defaults to "Solution").
 */
const SolutionDropdown = ({ code, explanation, label = "View Solution" }) => {
  return (
    <div style={{ margin: '20px 0', fontFamily: 'sans-serif' }}>
      <details style={{
        border: '1px solid #ddd',
        borderRadius: '8px',
        overflow: 'hidden'
      }}>
        <summary style={{
          fontWeight: 'bold',
          padding: '12px 16px',
          cursor: 'pointer',
          backgroundColor: '#f8f9fa',
          userSelect: 'none',
          listStyle: 'none' // Removes the default arrow in some browsers
        }}>
          ▼ {label}
        </summary>
        
        <div style={{ padding: '16px', backgroundColor: '#fff' }}>
          <p style={{ marginTop: 0, color: '#444', lineHeight: '1.5' }}>
            {explanation}
          </p>
          
          <pre style={{
            backgroundColor: '#1e1e1e', // VS Code style dark background
            color: '#d4d4d4',
            padding: '15px',
            borderRadius: '6px',
            overflowX: 'auto',
            fontSize: '14px',
            lineHeight: '1.4'
          }}>
            <code>{code}</code>
          </pre>
        </div>
      </details>
    </div>
  );
};

export default SolutionDropdown;