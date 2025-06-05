@Suppress("DEPRECATION")
class ModelResults : AppCompatActivity() {

    private lateinit var binding: ActivityModelResultsBinding
    private lateinit var imageUrl: String

    private lateinit var resView: TextView
    private lateinit var imageView: ImageView
    private var resultImageUri: Uri? = null

    private val TAG = "ModelResults"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModelResultsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        resView = view.findViewById(R.id.resView)
        imageView = view.findViewById(R.id.resultImage)

        imageUrl = intent.getStringExtra("imageUri") ?: ""
        val resultText = intent.getStringExtra("resultText") ?: ""

        binding.resView.text = resultText

        loadFirebaseImage(imageUrl)

        binding.save.setOnClickListener {
            saveResults(imageUrl, resultText)
        }

        binding.tryreco.setOnClickListener {
            navigateToChooseEventGenderActivity()
        }
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomnavigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    val intent = Intent(this, Classify::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_event -> {
                    val intent = Intent(this, Event::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_recommendation -> {
                    val intent = Intent(this, Recommendations::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
// Sample Codes for the Model Results

// Add actual code here
