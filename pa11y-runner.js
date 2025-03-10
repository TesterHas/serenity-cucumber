const pa11y = require('pa11y');
const fs = require('fs');

async function runPa11y(url) {
    try {
        const results = await pa11y(url, {
            includeWarnings: true,
            standard: 'WCAG2AA',
            log: {
                debug: console.log,
                error: console.error,
                info: console.info,
            },
        });

        // Write results to a JSON file
        fs.writeFileSync('pa11y-report.json', JSON.stringify(results, null, 2));
        console.log('Pa11y report generated: pa11y-report.json');

        if (results.issues.length > 0) {
            console.error('Accessibility issues found:');
            results.issues.forEach((issue) => {
                console.error(`- ${issue.message} on ${issue.selector}`);
            });
            process.exit(1); // Exit with error code
        } else {
            console.log('No accessibility issues found.');
            process.exit(0); // Exit with success code
        }
    } catch (error) {
        console.error('Error running Pa11y:', error.message);
        process.exit(1); // Exit with error code
    }
}

const url = process.argv[2];
if (!url) {
    console.error('Please provide a URL to test.');
    process.exit(1);
}

runPa11y(url);